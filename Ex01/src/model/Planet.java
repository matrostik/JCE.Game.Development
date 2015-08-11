/**
 * 
 */
package model;

/**
 * @author matro
 *
 */
public class Planet {

	// fields
	private String name;
	private Double gravity;
	private Double elasticity;
	
	// properties
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getGravity() {
		return gravity;
	}
	public void setGravity(Double gravity) {
		this.gravity = gravity;
	}
	public Double getElasticity() {
		return elasticity;
	}
	public void setElasticity(Double elasticit) {
		this.elasticity = elasticit;
	}
	
	public Planet(String name, Double gravity, Double elasticity)
	{
		this.name = name;
		this.gravity = gravity;
		this.elasticity = elasticity;
	}
	
	 public String toString() {
	        return this.name;
	    }
	
}
