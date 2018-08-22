package alarm_station;

import org.eclipse.etrice.runtime.java.messaging.*;
import org.eclipse.etrice.runtime.java.modelbase.*;
import org.eclipse.etrice.runtime.java.debugging.*;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;

import devices.*;
import logger.*;
import alarm_station.alarm_controller_iprotocol_t.*;
import devices.switch_protocol_t.*;

/*--------------------- begin user code ---------------------*/
import java.util.logging.*;
import java.io.IOException;

/*--------------------- end user code ---------------------*/


public class alarm_controller_t extends logger_t {


	//--------------------- ports
	protected alarm_controller_iprotocol_tPort iport = null;
	protected switch_protocol_tConjPort co_controller_port = null;
	protected switch_protocol_tConjPort o_controller_port = null;
	protected switch_protocol_tConjPort ch4_controller_port = null;
	protected switch_protocol_tConjPort water_flow_controller_port = null;

	//--------------------- saps

	//--------------------- services

	//--------------------- optional actors

	//--------------------- interface item IDs
	public static final int IFITEM_iport = 1;
	public static final int IFITEM_co_controller_port = 2;
	public static final int IFITEM_o_controller_port = 3;
	public static final int IFITEM_ch4_controller_port = 4;
	public static final int IFITEM_water_flow_controller_port = 5;

	/*--------------------- attributes ---------------------*/
	public  device_t alarm;
	public  int alarm_level;

	/*--------------------- operations ---------------------*/


	//--------------------- construction
	public alarm_controller_t(IRTObject parent, String name) {
		super(parent, name);
		setClassName("alarm_controller_t");

		// initialize attributes
		this.setAlarm(null);
		this.setAlarm_level(0);

		// own ports
		iport = new alarm_controller_iprotocol_tPort(this, "iport", IFITEM_iport);
		co_controller_port = new switch_protocol_tConjPort(this, "co_controller_port", IFITEM_co_controller_port);
		o_controller_port = new switch_protocol_tConjPort(this, "o_controller_port", IFITEM_o_controller_port);
		ch4_controller_port = new switch_protocol_tConjPort(this, "ch4_controller_port", IFITEM_ch4_controller_port);
		water_flow_controller_port = new switch_protocol_tConjPort(this, "water_flow_controller_port", IFITEM_water_flow_controller_port);

		// own saps

		// own service implementations

		// sub actors

		// wiring


		/* user defined constructor body */
		this.alarm_level = 0;

	}

	/* --------------------- attribute setters and getters */
	public void setAlarm(device_t alarm) {
		 this.alarm = alarm;
	}
	public device_t getAlarm() {
		return this.alarm;
	}
	public void setAlarm_level(int alarm_level) {
		 this.alarm_level = alarm_level;
	}
	public int getAlarm_level() {
		return this.alarm_level;
	}


	//--------------------- port getters
	public alarm_controller_iprotocol_tPort getIport (){
		return this.iport;
	}
	public switch_protocol_tConjPort getCo_controller_port (){
		return this.co_controller_port;
	}
	public switch_protocol_tConjPort getO_controller_port (){
		return this.o_controller_port;
	}
	public switch_protocol_tConjPort getCh4_controller_port (){
		return this.ch4_controller_port;
	}
	public switch_protocol_tConjPort getWater_flow_controller_port (){
		return this.water_flow_controller_port;
	}

	//--------------------- lifecycle functions
	public void stop(){
		super.stop();
	}

	public void destroy(){
		/* user defined destructor body */
		DebuggingService.getInstance().addMessageActorDestroy(this);
		super.destroy();
	}

	/* state IDs */
	public static final int STATE_waiting_for_imessage = 2;
	public static final int STATE_waiting_for_alarm_message = 3;
	public static final int STATE_MAX = 4;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__waiting_for_imessage = 1;
	public static final int CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_alarm_message_BY_initializeiport = 2;
	public static final int CHAIN_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received = 3;
	public static final int CHAIN_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received = 4;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_iport__initialize = IFITEM_iport + EVT_SHIFT*alarm_controller_iprotocol_t.IN_initialize;
	public static final int TRIG_co_controller_port__turn_on = IFITEM_co_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_co_controller_port__turn_off = IFITEM_co_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_o_controller_port__turn_on = IFITEM_o_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_o_controller_port__turn_off = IFITEM_o_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_ch4_controller_port__turn_on = IFITEM_ch4_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_ch4_controller_port__turn_off = IFITEM_ch4_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	public static final int TRIG_water_flow_controller_port__turn_on = IFITEM_water_flow_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_on;
	public static final int TRIG_water_flow_controller_port__turn_off = IFITEM_water_flow_controller_port + EVT_SHIFT*switch_protocol_t.OUT_turn_off;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"waiting_for_imessage",
		"waiting_for_alarm_message"
	};
	
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	
	/* Action Codes */
	protected void action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_alarm_message_BY_initializeiport(InterfaceItemBase ifitem, alarm_controller_idata_t data) {
	    this.alarm = data.alarm;
	}
	protected void action_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received(InterfaceItemBase ifitem) {
	    this.alarm_level++;
	    
	    if ( this.alarm_level == 1 ) {
	    	this.alarm.state = device_state_t.ON;	
	    	super.info ( super.getName ( ), "Initial turn on message recevied, turning on alarm" );
	    }
	}
	protected void action_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received(InterfaceItemBase ifitem) {
	    this.alarm_level--;
	    
	    if ( this.alarm_level == 0 ) {
	    	this.alarm.state = device_state_t.OFF;	
	    	super.info ( super.getName ( ), "Last turn off message recevied, turning off alarm" );
	    }
	}
	
	/* State Switch Methods */
	/**
	 * calls exit codes while exiting from the current state to one of its
	 * parent states while remembering the history
	 * @param current__et - the current state
	 * @param to - the final parent state
	 */
	private void exitTo(int current__et, int to) {
		while (current__et!=to) {
			switch (current__et) {
				case STATE_waiting_for_imessage:
					this.history[STATE_TOP] = STATE_waiting_for_imessage;
					current__et = STATE_TOP;
					break;
				case STATE_waiting_for_alarm_message:
					this.history[STATE_TOP] = STATE_waiting_for_alarm_message;
					current__et = STATE_TOP;
					break;
				default:
					/* should not occur */
					break;
			}
		}
	}
	
	/**
	 * calls action, entry and exit codes along a transition chain. The generic data are cast to typed data
	 * matching the trigger of this chain. The ID of the final state is returned
	 * @param chain__et - the chain ID
	 * @param generic_data__et - the generic data pointer
	 * @return the +/- ID of the final state either with a positive sign, that indicates to execute the state's entry code, or a negative sign vice versa
	 */
	private int executeTransitionChain(int chain__et, InterfaceItemBase ifitem, Object generic_data__et) {
		switch (chain__et) {
			case alarm_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage:
			{
				return STATE_waiting_for_imessage;
			}
			case alarm_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_alarm_message_BY_initializeiport:
			{
				alarm_controller_idata_t data = (alarm_controller_idata_t) generic_data__et;
				action_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_alarm_message_BY_initializeiport(ifitem, data);
				return STATE_waiting_for_alarm_message;
			}
			case alarm_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received:
			{
				action_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received(ifitem);
				return STATE_waiting_for_alarm_message;
			}
			case alarm_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received:
			{
				action_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received(ifitem);
				return STATE_waiting_for_alarm_message;
			}
				default:
					/* should not occur */
					break;
		}
		return NO_STATE;
	}
	
	/**
	 * calls entry codes while entering a state's history. The ID of the final leaf state is returned
	 * @param state__et - the state which is entered
	 * @return - the ID of the final leaf state
	 */
	private int enterHistory(int state__et) {
		if (state__et >= STATE_MAX) {
			state__et =  (state__et - STATE_MAX);
		}
		while (true) {
			switch (state__et) {
				case STATE_waiting_for_imessage:
					/* in leaf state: return state id */
					return STATE_waiting_for_imessage;
				case STATE_waiting_for_alarm_message:
					/* in leaf state: return state id */
					return STATE_waiting_for_alarm_message;
				case STATE_TOP:
					state__et = this.history[STATE_TOP];
					break;
				default:
					/* should not occur */
					break;
			}
		}
		/* return NO_STATE; // required by CDT but detected as unreachable by JDT because of while (true) */
	}
	
	public void executeInitTransition() {
		int chain__et = alarm_controller_t.CHAIN_TRANS_INITIAL_TO__waiting_for_imessage;
		int next__et = executeTransitionChain(chain__et, null, null);
		next__et = enterHistory(next__et);
		setState(next__et);
	}
	
	/* receiveEvent contains the main implementation of the FSM */
	public void receiveEventInternal(InterfaceItemBase ifitem, int localId, int evt, Object generic_data__et) {
		int trigger__et = localId + EVT_SHIFT*evt;
		int chain__et = NOT_CAUGHT;
		int catching_state__et = NO_STATE;
	
		if (!handleSystemEvent(ifitem, evt, generic_data__et)) {
			switch (getState()) {
			    case STATE_waiting_for_imessage:
			        switch(trigger__et) {
			                case TRIG_iport__initialize:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_imessage_received_FROM_waiting_for_imessage_TO_waiting_for_alarm_message_BY_initializeiport;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    case STATE_waiting_for_alarm_message:
			        switch(trigger__et) {
			                case TRIG_ch4_controller_port__turn_on:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_co_controller_port__turn_on:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_o_controller_port__turn_on:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_water_flow_controller_port__turn_on:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_on_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_onch4_controller_portturn_onco_controller_portturn_ono_controller_portturn_onwater_flow_controller_port_turn_on_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_ch4_controller_port__turn_off:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_co_controller_port__turn_off:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_o_controller_port__turn_off:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                case TRIG_water_flow_controller_port__turn_off:
			                    {
			                        chain__et = alarm_controller_t.CHAIN_TRANS_turn_off_message_received_FROM_waiting_for_alarm_message_TO_waiting_for_alarm_message_BY_turn_offch4_controller_portturn_offco_controller_portturn_offo_controller_portturn_offwater_flow_controller_port_turn_off_message_received;
			                        catching_state__et = STATE_TOP;
			                    }
			                break;
			                default:
			                    /* should not occur */
			                    break;
			        }
			        break;
			    default:
			        /* should not occur */
			        break;
			}
		}
		if (chain__et != NOT_CAUGHT) {
			exitTo(getState(), catching_state__et);
			{
				int next__et = executeTransitionChain(chain__et, ifitem, generic_data__et);
				next__et = enterHistory(next__et);
				setState(next__et);
			}
		}
	}
	public void receiveEvent(InterfaceItemBase ifitem, int evt, Object generic_data__et) {
		int localId = (ifitem==null)? 0 : ifitem.getLocalId();
		receiveEventInternal(ifitem, localId, evt, generic_data__et);
	}

};
