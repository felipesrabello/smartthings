/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Turn It On When It Opens After Sunset
 *
 *  Author: Felipe Rabello
 */
definition(
    name: "Turn On Only If I Open the Door After Sunset",
    namespace: "smartthings",
    author: "Felipe Rabello",
    description: "Turn something on only if you open the door after sunset.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet@2x.png"
)

preferences {
	section("When I open the door..."){
		input "contact1", "capability.contactSensor", title: "Where?"
	}
/**	section("Turn on a light..."){
		input "switch1", "capability.switchLevel", multiple: true
	}*/
    section("Turn on a light..."){
		input "switche1", "capability.switch", multiple: true
	}
}

def installed()
{
	subscribe(contact1, "contact.open", contactOpenHandler)
}

def updated()
{
	unsubscribe()
	subscribe(contact1, "contact.open", contactOpenHandler)
}

def contactOpenHandler(evt)
{
	def now = new Date()
	def sunTime = getSunriseAndSunset(sunsetOffset: "+00:30")
;
                                                         // Itens que vão listar seus valores no Debug
	log.debug "nowTime: $now"                            // Hora Atual
	log.debug "riseTime: $sunTime.sunrise"               // Nascer do Sol
	log.debug "setTime: $sunTime.sunset"                 // Por do Sol
	log.debug "contactOpenHandle $evt.name: $evt.value"
    
	def current = contact1.currentValue("open")
	log.debug current
	/*  def contactValue = contact1.find{it.currentPresence == "present"}
	log.debug presenceValue */
	if(now > sunTime.sunset) {
		/switch1.setLevel(100)
        switch1.on()
		log.debug "Welcome home at night!"
	}
    else if(now < sunTime.sunrise) {
    	/switch1.setLevel(100)
        switch1.on()
        log.debug "Welcome home at daytime!"
    }
	else {
		log.debug "Everyone's away."
	}
}