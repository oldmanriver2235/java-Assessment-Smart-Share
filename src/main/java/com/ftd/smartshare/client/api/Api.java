package com.ftd.smartshare.client.api;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ftd.smartshare.dto.RequestDto;
import com.ftd.smartshare.dto.ResponseDto;

public final class Api {

	private static final String HOST = "localhost";
	private static final int PORT = 3000;

	private Api() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Send download request
	 *
	 * @param downloadRequestDto JAXB annotated class representing the download
	 *                           request
	 * @return true if request was successful and false if unsuccessful
	 */
	public static boolean download(RequestDto requestDto) {

		try (Socket socket = new Socket(HOST, PORT);) {
			JAXBContext context = JAXBContext.newInstance(RequestDto.class, ResponseDto.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(requestDto, socket.getOutputStream());
			socket.shutdownOutput();

			Unmarshaller unmarshaller = context.createUnmarshaller();
			ResponseDto response = (ResponseDto) unmarshaller.unmarshal(socket.getInputStream());

			if (response.getError() == null) {
				Path path = Paths.get("downloads");
				try {
					Files.createDirectories(path);
					Files.write(Paths.get(path + "/" + response.getName()), response.getFile());
					System.out.println("Download File" + response.getName() + "successfully!");
					return true;

				} catch (IOException e) {
					System.out.println("awww snap!");
				}
			} else {
				System.out.println(response.getError());
			}
			return false;
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Send upload request
	 *
	 * @param uploadRequestDto JAXB annotated class representing the upload request
	 * @return true if request was successful and false if unsuccessful
	 */
	public static boolean upload(RequestDto requestDto) {
		try {

			Socket socket = new Socket(HOST, PORT);

			JAXBContext context = JAXBContext.newInstance(RequestDto.class, ResponseDto.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.marshal(requestDto, socket.getOutputStream());
			socket.shutdownOutput();

			Unmarshaller unmarshaller = context.createUnmarshaller();

			ResponseDto response = (ResponseDto) unmarshaller.unmarshal(socket.getInputStream());

			if (response.getSuccess() != null) {
				System.out.println(response.getSuccess());
			} else {
				System.out.println(response.getError());
			}

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
