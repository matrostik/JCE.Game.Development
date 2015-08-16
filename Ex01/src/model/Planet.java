/**
 * 
 */
package model;

/**
 * Planet presentation class
 */
public class Planet {

	// fields
	private String name;
	private Double gravity;
	private Double elasticity;
	private Boolean isEditable;
	
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
	
	public Boolean getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}
	
	public Planet(String name, Double gravity, Double elasticity, boolean isEditable)
	{
		this.name = name;
		this.gravity = gravity;
		this.elasticity = elasticity;
		this.isEditable = isEditable;
	}
	
	 public String toString() {
	        return this.name;
	    }
	
}
