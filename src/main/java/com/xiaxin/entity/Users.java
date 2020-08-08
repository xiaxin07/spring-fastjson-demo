package com.xiaxin.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Users implements Serializable {

	private Long userId;
	private String name;
	private Integer age;
	private Date createDate;

}
