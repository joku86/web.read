package web.read;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Index;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
 public class Zwangsversteigerungen extends BaseEntity{
	public Zwangsversteigerungen(Date datum, String zwangsversteigerungID, String aktenzeichen, LocalDateTime termin, String adresse) {
		this.datum = datum;
		this.zwangsversteigerungID = zwangsversteigerungID;
		this.aktenzeichen = aktenzeichen;
		this.termin = termin;
		this.adresse = adresse;
	}

	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Date datum;
	private String zwangsversteigerungID;

	public Zwangsversteigerungen() {
	}

	public String getAktenzeichen() {
		return aktenzeichen;
	}

	public void setAktenzeichen(String aktenzeichen) {
		this.aktenzeichen = aktenzeichen;
	}

	public LocalDateTime getTermin() {
		return termin;
	}

	public void setTermin(LocalDateTime termin) {
		this.termin = termin;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	private String aktenzeichen;
	private LocalDateTime termin;
	private String adresse;




	public Zwangsversteigerungen(Date datum, String zwangsversteigerungID) {
		this.datum = datum;
		this.zwangsversteigerungID = zwangsversteigerungID;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getZwangsversteigerungID() {
		return zwangsversteigerungID;
	}

	public void setZwangsversteigerungID(String zwangsversteigerungID) {
		this.zwangsversteigerungID = zwangsversteigerungID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Zwangsversteigerungen that = (Zwangsversteigerungen) o;


		if (zwangsversteigerungID != null ? !zwangsversteigerungID.equals(that.zwangsversteigerungID) : that.zwangsversteigerungID != null)
			return false;
		if (aktenzeichen != null ? !aktenzeichen.equals(that.aktenzeichen) : that.aktenzeichen != null) return false;
		if (termin != null ? !termin.equals(that.termin) : that.termin != null) return false;
		return adresse != null ? adresse.equals(that.adresse) : that.adresse == null;
	}

	@Override
	public int hashCode() {
		int result = datum != null ? datum.hashCode() : 0;
		result = 31 * result + (zwangsversteigerungID != null ? zwangsversteigerungID.hashCode() : 0);
		result = 31 * result + (aktenzeichen != null ? aktenzeichen.hashCode() : 0);
		result = 31 * result + (termin != null ? termin.hashCode() : 0);
		result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
		return result;
	}
}
