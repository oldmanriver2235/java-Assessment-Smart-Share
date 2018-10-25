package com.ftd.smartshare.server;

import java.io.IOException;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ftd.smartshare.dto.RequestDto;
import com.ftd.smartshare.dto.ResponseDto;
import com.ftd.smartshare.server.dao.FileDao;
import com.ftd.smartshare.server.entity.File;
import com.ftd.smartshare.utils.NoCloseInputStream;

public class ClientHandler implements Runnable {

	    private Socket clientSocket;

	    public ClientHandler (Socket clientSocket) {
	        this.clientSocket = clientSocket;
	    }

	    public void run () {
	    	FileDao fileDao = new FileDao();
	        
	        try {
	        	// create JAXB context and unmarshal the UploadDto
	        	JAXBContext context = JAXBContext.newInstance(RequestDto.class, ResponseDto.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				RequestDto request = (RequestDto) unmarshaller.unmarshal(new NoCloseInputStream(clientSocket.getInputStream()));
	        	
	        	if (request.getType().equals("upload")) {
		        	File fileToSave = new File(request.getFilename(), request.getFile());
			        
		        	if (fileDao.save(fileToSave)) {
						Marshaller marshaller = context.createMarshaller();
	
						marshaller.marshal(new ResponseDto("File saved successfully", null), clientSocket.getOutputStream());
			        				
		        	}
	        	} else if (request.getType().equals("download")) {
	        		
	        		File file = FileDao.getByName(request.getFilename());
	
					Marshaller marshaller = context.createMarshaller();

					marshaller.marshal(new ResponseDto("File saved successfully", file.getFilename(), file.getFile()), clientSocket.getOutputStream());
	        	} else {
	        		
	        	}
	        } catch (JAXBException | IOException e) {
	        	e.printStackTrace();
	        }
	   }
}
