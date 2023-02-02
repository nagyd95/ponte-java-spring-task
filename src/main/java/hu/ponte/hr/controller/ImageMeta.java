package hu.ponte.hr.controller;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageMeta
{
	private String id ;
	private String name;
	private String mimeType;
	private long size;
	private String digitalSign;
}

