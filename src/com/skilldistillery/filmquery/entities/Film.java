package com.skilldistillery.filmquery.entities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Film {

	public Film() {
		super();
	}

	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}

	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures,
			List<Actor> castList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.castList = castList;
	}

	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> castList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return releaseYear;
	}

	public void setYear(Integer year) {
		this.releaseYear = year;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((castList == null) ? 0 : castList.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + languageId;
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		result = prime * result + rentalDuration;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(replacementCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeatures == null) ? 0 : specialFeatures.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (castList == null) {
			if (other.castList != null)
				return false;
		} else if (!castList.equals(other.castList))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (languageId != other.languageId)
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (rentalDuration != other.rentalDuration)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(replacementCost) != Double.doubleToLongBits(other.replacementCost))
			return false;
		if (specialFeatures == null) {
			if (other.specialFeatures != null)
				return false;
		} else if (!specialFeatures.equals(other.specialFeatures))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", releaseYear=").append(releaseYear).append(", languageId=")
				.append(languageId).append(", rentalDuration=").append(rentalDuration).append(", rentalRate=")
				.append(rentalRate).append(", length=").append(length).append(", replacementCost=")
				.append(replacementCost).append(", rating=").append(rating).append(", specialFeatures=")
				.append(specialFeatures).append(", castList=").append(castList).append("]");
		return builder.toString();
	}

	// title, year, rating, and description
	public String userToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Title: ").append(convertToTitle(title)).append("\n").append(convertToTitle(title)).append(", was released in ").append(releaseYear)
		.append(" with a rating of ").append(rating).append(".").append("\nDescription:\n	").append(description);
		return builder.toString();
	}

	// Use of a stream. Arrays.stream().map(a non-interfering, stateless
	// function).collect(Collectors.joining(" "));
	public static String convertToTitle(String text) {
		if (text == null || text.isEmpty()) {
			return text;
		}

		return Arrays.stream(text.split(" ")).map(
				word -> word.isEmpty() ? word : Character.toTitleCase(word.charAt(0)) + word.substring(1).toLowerCase())
				.collect(Collectors.joining(" "));
	}

}
