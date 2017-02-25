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
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/tmp")
public class SendPicture {

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> hello(@PathVariable String id) {
		byte[] image;
		try {
			Path path = Paths.get("/tmp",id + ".jpg");
			System.out.println(path.toString());
			image = Files.readAllBytes(path);

		} catch (InvalidPathException | IOException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(image);
	}
}
