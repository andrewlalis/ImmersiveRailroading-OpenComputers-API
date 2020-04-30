# ImmersiveRailroading-OpenComputers-API
A simple REST API that provides persistence and automation for [Immersive Railroading](https://github.com/TeamOpenIndustry/ImmersiveRailroading) and [OpenComputers](https://ocdoc.cil.li/). By running this API server on same machine as your Minecraft server or singleplayer world, it can communicate with your in-game computers via OpenComputer's [Internet API](https://ocdoc.cil.li/api:internet) via HTTP requests.

Along with the generic endpoints that are exposed by running this API, an `oc_scripts` folder contains more information about how to set up your computers in-game, as well as some sample implementations.

## Rolling Stock Tracking
Each time a piece of rolling stock (locomotive or railway wagon / passenger car) moves over an Immersive Railroading detector augment, there's an opportunity to record some data:
* The rolling stock's metadata: things like its name, cargo or fluid capacity and storage, tags, etc.
* Speed (in Km/h)
* Direction
* Location (this can be taken from the detector).

By setting up a computer connected to such a detector augment, every time a rolling stock moves over the detector an HTTP request can be sent to the API to record the data. This way, users can query the API to get the most up-to-date information about where a piece of rolling stock is in their system, and what it's currently doing.

## Rail Networks as a Graph

