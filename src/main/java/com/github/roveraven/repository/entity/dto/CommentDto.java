package com.github.roveraven.repository.entity.dto;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private long id;
	private long userId;
	private String username;
	private String text;
	private long parentCommentId;
	private long topId;
	private int level;
	private ZonedDateTime time;

}
