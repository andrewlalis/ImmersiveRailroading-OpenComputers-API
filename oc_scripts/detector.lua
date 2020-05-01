local component = require("component")
local event = require("event")
local thread = require("thread")
local net = require("internet")
local json = require("json")

-- The unique token for your world. If you don't have one yet, you can create one by posting to /worlds
local WORLD_TOKEN = "imokvBK0D"
local BASE_URL = "http://localhost:8567/worlds/%s/rolling_stocks/%s/status"

-- Sends a POST request to the given URL, with a JSON body.
local function sendJsonPost(url, data)
  local headers = {
    ["Content-Type"]="application/json"
  }
  local handle = net.request(url, json.encode(data), headers, "POST")
  if handle == nil then
    print("Could not perform request to url: " .. url)
    return
  end
  local result = ""
  for chunk in handle do result = result .. chunk end
  local mt = getmetatable(handle)
  local code, message, headers = mt.__index.response()
  return {
    code=code,
    message=message,
    content=result
  }
end

-- Retrieves data from a detector, formats it, and sends it to the API.
local function handleDetect(detector, stock_uuid)
  print("Detected Stock with UUID: " .. stock_uuid)
  local info = detector.info()
  local x,y,z = detector.getPos()
  local data = {
    position={x=x, y=y, z=z},
    jsonRollingStockId=info.id,
    name=info.name,
    cargoSize=info.cargo_size,
    speed=info.speed,
    weight=info.weight,
    passengers=info.passengers,
    direction=info.direction,
    tag=info.tag
  }

  local url = string.format(BASE_URL, WORLD_TOKEN, stock_uuid)
  print("Sending status to " .. url)
  local result = sendJsonPost(url, data)
  print("Response code: " .. result.code .. ", Message: " .. result.message)
  print("Response body: \n" .. result.content)
end

-- Constantly wait for a detector to fire an event, then process it in another thread.
while true do
  print("Waiting for detection event.")
  event_name, address, augment_type, stock_uuid = event.pull("ir_train_overhead")
  if (augment_type == "DETECTOR" and stock_uuid ~= nil) then
    thread.create(handleDetect, component.proxy(address), stock_uuid)
  end
end