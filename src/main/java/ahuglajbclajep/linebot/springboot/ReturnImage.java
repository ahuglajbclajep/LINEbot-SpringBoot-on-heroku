package ahuglajbclajep.linebot.springboot;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;


@RestController
@RequestMapping("/tmp")
public class ReturnImage {


	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> hello(@PathVariable String id) {
		byte[] image;
		try {
			image = Files.readAllBytes(Paths.get("/tmp",id + ".jpg"));
		} catch (InvalidPathException | IOException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(image);
	}
}
