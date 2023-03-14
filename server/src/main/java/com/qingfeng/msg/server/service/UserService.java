package com.qingfeng.msg.server.service;


import com.google.protobuf.InvalidProtocolBufferException;
import com.qingfeng.msg.common.annotation.Cmd;
import com.qingfeng.msg.common.annotation.Module;
import com.qingfeng.msg.common.protobuf.Result;
import com.qingfeng.msg.common.protobuf.UserModule;
import org.springframework.stereotype.Service;

@Service
@Module(module = "user")
public class UserService {

	@Cmd(cmd = "save")
	public Result<?> save(byte[] data) {
		UserModule.User user = null;
		try {
			user = UserModule.User.parseFrom(data);
			System.err.println(" save ok , userId: " + user.getUserId() + " ,userName: " + user.getUserName());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			return Result.FAILURE();
		}
		return Result.SUCCESS(user);
	}
	
	@Cmd(cmd = "update")
	public Result<?> update(byte[] data) {
		UserModule.User user = null;
		try {
			user = UserModule.User.parseFrom(data);
			System.err.println(" update ok , userId: " + user.getUserId() + " ,userName: " + user.getUserName());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			return Result.FAILURE();
		}
		return Result.SUCCESS(user);
	}
	
}
