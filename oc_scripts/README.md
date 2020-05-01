# OpenComputers Scripts
In order to interact with the IR-OC-API, the first step is to be able to send data out of your minecraft world, and receive data from the outside world. To facilitate this, computers within your world can be equipped with an [Internet Card](https://ocdoc.cil.li/item:internet_card). With the internet card, your computer can make HTTP requests to the API. Although there are no strict rules about what you can and can't do with the API, this directory contains some sample implementations for the most common use-cases.

### JSON
For most, if not all of the functionality exposed by the API, `json` is the required medium by which data is transferred between the API and your computers or other devices. However, OpenComputers' computers use **Lua**, which has its own table syntax. In order to convert between the two, you may use any JSON serializer library you like, however for the example implementations provided here, we'll use [json.lua](https://github.com/rxi/json.lua).

> For your convenience, this library has been copied into a [pastebin](https://pastebin.com/zJm2jgtu) file. If you'd like to install json.lua on your OC computer, simply do `pastebin get zJm2jgtu /lib/json.lua`

Once you've installed it, using this library is very simple, as can be shown with this example:

```lua
local json = require("json")
json.encode({ 1, 2, 3, { x = 10 } }) -- Returns '[1,2,3,{"x":10}]'
json.decode('[1,2,3,{"x":10}]') -- Returns { 1, 2, 3, { x = 10 } }
```

## Simple Detector Tracking
To keep track of where your trains are, the simplest way to do so is to lay down some detector augments along your track, and hook up computers that send data to the API. The `detector.lua` script does this in the following way:
* Waits for any detection event.
* Starts a new thread.
* In that thread, the detector's data is first processed into the format required by the API.
* Then, the data is converted to a JSON string and posted.