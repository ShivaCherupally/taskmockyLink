package com.example.shiva.taskshiva;

/**
 * Created by Shiva on 7/5/2017.
 */

public class ItemObjects
{
	private String name;
	private String photoUrl;

	public ItemObjects(final String name, final String photoUrl)
	{
		this.name = name;
		this.photoUrl = photoUrl;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public String getPhotoUrl()
	{
		return photoUrl;
	}

	public void setPhotoUrl(final String photoUrl)
	{
		this.photoUrl = photoUrl;
	}
}
