/**
 *  Turn your Light on when detect motion in set time
 *
 *  Copyright 2015 Felipe Rabello
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
 */
definition(
    name: "Turn your Light on when detect motion in set time",
    namespace: "Smartthings",
    author: "Felipe Rabello",
    description: "Ligar as luzes se detectar movimento em periodo de tempo determinado",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet@3x.png")


preferences {
	section("When there's movement...") {
		input "motion1", "capability.motionSensor", title: "Where?", multiple: true
	}
	/**section("Turn on a light...") {
		input "switch1", "capability.switchLevel", multiple: true
	}
    section("Dimmer %...") {
    	input "dimmerLevel", "number"
    }*/
    section("Turn on a light..."){
		input "switche1", "capability.switch", multiple: true
	}
    section("Time Period...") {
		input "startTime", "time"
		input "endTime", "time"
	}
    section("Minutes Delay...") {
    	input "delay", "number"
    }
}

def installed()
{
	subscribe(motion1, "motion.active", motionActiveHandler)
}

def updated()
{
	unsubscribe()
	subscribe(motion1, "motion.active", motionActiveHandler)
}

def motionActiveHandler(evt) {
	def start = timeToday(startTime, location.timeZone)
	def end = timeToday(endTime, location.timeZone)
    def now = new Date()
    def MinuteDelay = 60 * delay
    //def switchstatus = switch1.currentSwitch
    /*log.debug "Presence is active:"
    log.debug "Time Start : $start"
    log.debug "Time End : $end"
    log.debug "Time Now : $now"*/
	
    if (now > start && now < end) {
    	//log.debug "Switchstatus deve on : $switchstatus"
        //switch1.setLevel(dimmerLevel)
        switch1.on()
        runIn(MinuteDelay, turnOffSwitch)
        }
    else  {
    	log.debug "Houve movimento mas fora do horário"
        // switch1.off()
    }
	
}
def turnOffSwitch() {
	//switch1.setLevel(99)
	//switch1.setLevel(0)
    switch1.off()
    //log.debug "Switchstatus deve ser off : $switchstatus"
}