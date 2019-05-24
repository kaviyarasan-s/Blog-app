package com.chainsys.model;

public class Post {
private int id;
private String title;
private String content;
private String url;
private int userId;
private Catogery catogery;
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
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public Catogery getCatogery() {
	return catogery;
}
public void setCatogery(Catogery catogery) {
	this.catogery = catogery;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((catogery == null) ? 0 : catogery.hashCode());
	result = prime * result + ((content == null) ? 0 : content.hashCode());
	result = prime * result + id;
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	result = prime * result + ((url == null) ? 0 : url.hashCode());
	result = prime * result + userId;
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
	Post other = (Post) obj;
	if (catogery == null) {
		if (other.catogery != null)
			return false;
	} else if (!catogery.equals(other.catogery))
		return false;
	if (content == null) {
		if (other.content != null)
			return false;
	} else if (!content.equals(other.content))
		return false;
	if (id != other.id)
		return false;
	if (title == null) {
		if (other.title != null)
			return false;
	} else if (!title.equals(other.title))
		return false;
	if (url == null) {
		if (other.url != null)
			return false;
	} else if (!url.equals(other.url))
		return false;
	if (userId != other.userId)
		return false;
	return true;
}
@Override
public String toString() {
	return "Post [id=" + id + ", title=" + title + ", content=" + content + ", url=" + url + ", userId=" + userId
			+ ", catogery=" + catogery + "]";
}

}
