package com.ftd.smartshare.client.commands.subcommands;

import com.ftd.smartshare.client.api.Api;
import com.ftd.smartshare.dto.RequestDto;
import com.ftd.smartshare.utils.PasswordGenerator;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@CommandLine.Command(
        description = "Uploads file using a given 'password', expiration (60 minutes by default), a max downloads (1 by default)",
        name = "upload",
        aliases = "u",
        mixinStandardHelpOptions = true
)
public class Upload implements Runnable {

    @CommandLine.Parameters(arity="1", index = "0", description = "The file to be uploaded")
    private File file;

    @CommandLine.Parameters(arity="0", index = "1", description = "The password for the file")
    private String password = PasswordGenerator.generate();

    public void run() {
        System.out.println("Uploading: " + file.getAbsolutePath());
        System.out.println("Password will be printed below");
        System.out.println(password);


        // Read in the file. Create an RequestDto of type "upload". Call Api.upload(requestDto)
        try (InputStream fileInputStream = new FileInputStream(file);) {
        	byte[] bytes = new byte[fileInputStream.available()];
        	fileInputStream.read(bytes);
        	Api.upload(new RequestDto("upload", file.getName(), bytes));
        } catch (IOException e) {
        	
        }

  }
}



