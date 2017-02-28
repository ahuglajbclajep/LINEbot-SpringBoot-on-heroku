package ahuglajbclajep.linebot.springboot;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;


@SpringBootApplication
@LineMessageHandler
public class CallBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(CallBackApplication.class, args);
	}


	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);
		List<Message> reply = new ArrayList<>();
		String[] args = event.getMessage().getText().split(" ", 2);

		if ("@qr".equals(args[0])) {
			reply.add(new TextMessage("よぉーし、頑張るにゃ！"));
			try {
				String qr = createQR(args[1], event.getMessage().getId());

				String url = new StringBuffer("https://")
						.append(System.getenv("APP_NAME"))
						.append(".herokuapp.com")
						.append(qr)
						.toString();
				reply.add(new ImageMessage(url, url));

			} catch (ArrayIndexOutOfBoundsException | IOException | WriterException e) {
				reply.add(new TextMessage("およ？およよ？"));
			}

		} else if ("@time".equals(args[0])) {
			reply.add(new TextMessage("えへへ、どうぞです♪"));
			try {
				ZonedDateTime now = ZonedDateTime.now(ZoneId.of(args[1]));
				reply.add(new TextMessage(now.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"))));

			} catch (ArrayIndexOutOfBoundsException | DateTimeException e) {
				StringBuffer sb = new StringBuffer("利用可能なタイムゾーンの一覧です！")
						.append(System.getProperty("line.separator"))
						.append("https://git.io/vyqDP");
				reply.add(new TextMessage(sb.toString()));
			}

		} else if ("@wol".equals(args[0])) {
			reply.add(new TextMessage("はい、睦月が用意するね！"));
			try{
				String url = URLEncoder.encode(args[1], "UTF-8");
				reply.add(new TextMessage("http://www.wolframalpha.com/input/?i=" + url));

			}catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e){
				reply.add(new TextMessage("http://www.wolframalpha.com"));
			}

		} else {
			reply.add(new TextMessage("にゃしぃ"));
		}

		execute(new ReplyMessage(event.getReplyToken(), reply));
	}


	@EventMapping
	public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent> event) {
		System.out.println("event: " + event);
		return new TextMessage("睦月、負ける気がしないのね！");
	}


	@EventMapping
	public TextMessage handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
		System.out.println("event: " + event);
		return new TextMessage("なんですかなんですかぁー？");
	}


	@EventMapping
	public TextMessage handleFollowEvent(FollowEvent event) {
		System.out.println("event: " + event);
		return new TextMessage("睦月、砲雷撃戦始めるよ♪");
	}


	@EventMapping
	public TextMessage handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
		return new TextMessage("睦月、呼んだ？");
	}


	private void execute(ReplyMessage replyMessage) {
		try {
			LineMessagingServiceBuilder.create(System.getenv("LINE_BOT_CHANNEL_TOKEN")).build().replyMessage(replyMessage).execute();
		} catch (IOException e) {}
	}


	private String createQR(String arg, String id) throws WriterException, IOException {
		EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);

		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new QRCodeWriter().encode(arg, BarcodeFormat.QR_CODE, 185, 185, hints);
		// サイズはバージョン毎に4セル刻みで大きくなり,バージョン40で171x171, デフォルトのMARGINは上下左右4セル

		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

		String fileName = new StringBuffer("/tmp/").append(id).append(".jpg").toString();
		ImageIO.write(image, "JPEG", new File(fileName));  // tmpフォルダ以下に出力

		return fileName;
	}
}
