package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;



public class water_level_detector_controller_base_idata_t extends environment_station_actor_base_idata_t implements Serializable {

	private static final long serialVersionUID = -640535664L;


	/*--------------------- attributes ---------------------*/
	public  water_level_detector_t water_level_detector;

	/* --------------------- attribute setters and getters */
	public void setWater_level_detector(water_level_detector_t water_level_detector) {
		 this.water_level_detector = water_level_detector;
	}
	public water_level_detector_t getWater_level_detector() {
		return this.water_level_detector;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public water_level_detector_controller_base_idata_t() {
		super();

		// initialize attributes
		this.setWater_level_detector(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public water_level_detector_controller_base_idata_t(int period, water_level_detector_t water_level_detector) {
		super(period);

		this.water_level_detector = water_level_detector;

		/* user defined constructor body */
	}

	// deep copy
	public water_level_detector_controller_base_idata_t deepCopy() {
		water_level_detector_controller_base_idata_t copy = new water_level_detector_controller_base_idata_t();
		copy.period = period;
		copy.water_level_detector = water_level_detector;
		return copy;
	}
};