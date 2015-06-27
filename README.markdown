# SmartThings apps

This repo contains my apps for SmartThings. 

## Yale Lock Actions

The Yale Z-Wave locks send more detailed commands than simple lock and unlock. This app lets you assign Hello, Home actions to four of the different actions the Yale lock triggers. 

Note that SmartThings doesn't explicitly send the payload or Z-Wave parameters where the additional lock codes are reported. Instead, you have to extract those parameters from the `event.description` field which is a string that includes the payload. 

If you were to do this implementation properly, you would want to create a custom device type that sends different events based on the Z-Wave parameters, but I didn't have the time or knowledge to do that (and this works).

Please feel free to fork and enhance this app!

## Installation

Create a Smart App at [https://graph.api.smartthings.com](https://graph.api.smartthings.com). Copy the `.groovy` code from this repo into the editor. Save and publish and the app will show up under "My Apps" in the SmartThings app.