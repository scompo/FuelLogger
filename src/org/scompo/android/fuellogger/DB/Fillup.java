package org.scompo.android.fuellogger.DB;

/**
 * Implements a single fillup to be used in the DB.
 * 
 * @author scompo
 * @version 1.0
 */
public class Fillup {
	
	//public constants
	/**
	 * ID name on DB.
	 */
	public static final String COLUMN_NAME_ID ="id";
	
	/**
	 * QT name on DB.
	 */
	public static final String COLUMN_NAME_QT ="quantity";
	
	/**
	 * Price name on DB:
	 */
	public static final String COLUMN_NAME_PRICE ="price";
	
	/**
	 * Date name on DB.
	 */
	public static final String COLUMN_NAME_DATE ="date_stored";
	
	/**
	 * Distance name on DB.
	 */
	public static final String COLUMN_NAME_DISTANCE ="distance";
	
	/**
	 * Partial field name on DB.
	 */
	public static final String COLUMN_NAME_PARTIAL ="partial";
	
	//private internal data.
	private int id;
	private float qt;
	private float price;
	private String date;
	private long distance;
	private boolean partial;
	
	/**
	 * Constructor.
	 * 
	 * @param id id.
	 * @param qt qt.
	 * @param price price.
	 * @param date date.
	 * @param distance distance.
	 * @param partial if partial or full fillup.
	 */
	public Fillup(int id, float qt, float price, String date, long distance, boolean partial) {
		this.id = id;
		this.qt = qt;
		this.price = price;
		this.date = date;
		this.distance = distance;
		this.partial=partial;
	}
	
	/**
	 * Empty constructor.
	 */
	public Fillup(){
		this(0, 0, 0, null, 0,false);
	}
	
	/**
	 * Constructor with just the id.
	 * 
	 * @param id the id.
	 */
	public Fillup(int id){
		this(id,0,0,null, 0,false);
	}

	@Override
	public String toString(){
		return ""+ date +" "+ distance +" "+ qt+" "+price +" "+ partial;
	}
	
	/**
	 * @return the partial
	 */
	public boolean isPartial() {
		return partial;
	}

	/**
	 * @param partial the partial to set
	 */
	public void setPartial(boolean partial) {
		this.partial = partial;
	}

	/**
	 * @return the distance
	 */
	public long getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(long distance) {
		this.distance = distance;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the qt
	 */
	public float getQt() {
		return qt;
	}
	
	/**
	 * @param qt the qt to set
	 */
	public void setQt(float qt) {
		this.qt = qt;
	}
	
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
}
