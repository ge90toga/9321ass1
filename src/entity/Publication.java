package entity;

import java.io.Serializable;
import java.util.ArrayList;
import util.StringUtil;

public class Publication implements Serializable{
	
	/**
	 * ies|school|chapter
	 */
	//10 attrs for simplicity
	private String type;
	private String title;
	private ArrayList<String> author_or_editors = new ArrayList<>();
	private String booktitle;
	private String pages;
	private String year;
	private String address;
	private String volume;
	private String number;
	private String url;
	
	private String journal;
	private String month;
	private String ee;
	private String cdrom;
	private String cite;
	private String publisher;
	private String note;
	private String crossref;
	private String isbn;
	private String series;
	private String ieString;
	private String school;
	private String chapter;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<String> getAuthor_or_editors() {
		return author_or_editors;
	}
	
	public void add_Author_or_editors(String author_or_editors) {
		this.author_or_editors.add(author_or_editors);
	}
	
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Override
	public String toString(){
		StringBuffer returnedBufferdStr = new StringBuffer("Type: "+type);
		returnedBufferdStr.append(" title="+title);
		
		String authors = toStringAuthors();
		
		if(authors.length() > 0){
			returnedBufferdStr.append(" Authors="+authors);
		}

		if(!StringUtil.isNull(pages)){
			returnedBufferdStr.append(" pages="+pages);
		}
		
		if(!StringUtil.isNull(year)){
			returnedBufferdStr.append(" year="+year);
		}
		
		if(!StringUtil.isNull(address)){
			returnedBufferdStr.append(" address="+address);
		}
		
		if(!StringUtil.isNull(volume)){
			returnedBufferdStr.append(" volume="+volume);
		}
		
		if(!StringUtil.isNull(number)){
			returnedBufferdStr.append(" number="+number);
		}
		
		if(!StringUtil.isNull(url)){
			returnedBufferdStr.append(" url="+url);
		}
		
		return returnedBufferdStr.toString();
	}
	
	public String toStringAuthors(){
		String returnedStr = "";
		for(String author: author_or_editors){
			if(this.author_or_editors.size()>1){
				returnedStr+=" "+author+" |";
			}else{
				returnedStr+=" "+author;
			}
			
		}
		return returnedStr;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getEe() {
		return ee;
	}

	public void setEe(String ee) {
		this.ee = ee;
	}

	public String getCdrom() {
		return cdrom;
	}

	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite += cite+"//";
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCrossref() {
		return crossref;
	}

	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIeString() {
		return ieString;
	}

	public void setIeString(String ieString) {
		this.ieString = ieString;
	}

}
