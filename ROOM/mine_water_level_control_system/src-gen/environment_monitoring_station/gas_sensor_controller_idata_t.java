package environment_monitoring_station;

import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;
import java.io.Serializable;

import devices.*;



public class gas_sensor_controller_idata_t extends environment_station_actor_base_idata_t implements Serializable {

	private static final long serialVersionUID = 1328428699L;


	/*--------------------- attributes ---------------------*/
	public  boolean detect_above_threshold;
	public  int threshold;
	public  gas_sensor_t gas_sensor;
	public  int error_count_threshold;

	/* --------------------- attribute setters and getters */
	public void setDetect_above_threshold(boolean detect_above_threshold) {
		 this.detect_above_threshold = detect_above_threshold;
	}
	public boolean getDetect_above_threshold() {
		return this.detect_above_threshold;
	}
	public void setThreshold(int threshold) {
		 this.threshold = threshold;
	}
	public int getThreshold() {
		return this.threshold;
	}
	public void setGas_sensor(gas_sensor_t gas_sensor) {
		 this.gas_sensor = gas_sensor;
	}
	public gas_sensor_t getGas_sensor() {
		return this.gas_sensor;
	}
	public void setError_count_threshold(int error_count_threshold) {
		 this.error_count_threshold = error_count_threshold;
	}
	public int getError_count_threshold() {
		return this.error_count_threshold;
	}

	/*--------------------- operations ---------------------*/

	// default constructor
	public gas_sensor_controller_idata_t() {
		super();

		// initialize attributes
		this.setGas_sensor(null);

		/* user defined constructor body */
	}

	// constructor using fields
	public gas_sensor_controller_idata_t(int period, boolean detect_above_threshold, int threshold, gas_sensor_t gas_sensor, int error_count_threshold) {
		super(period);

		this.detect_above_threshold = detect_above_threshold;
		this.threshold = threshold;
		this.gas_sensor = gas_sensor;
		this.error_count_threshold = error_count_threshold;

		/* user defined constructor body */
	}

	// deep copy
	public gas_sensor_controller_idata_t deepCopy() {
		gas_sensor_controller_idata_t copy = new gas_sensor_controller_idata_t();
		copy.period = period;
		copy.detect_above_threshold = detect_above_threshold;
		copy.threshold = threshold;
		copy.gas_sensor = gas_sensor;
		copy.error_count_threshold = error_count_threshold;
		return copy;
	}
};
