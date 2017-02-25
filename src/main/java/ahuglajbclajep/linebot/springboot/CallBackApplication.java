package ahuglajbclajep.linebot.springboot;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

		String[] spl = event.getMessage().getText().split(" ", 2);
		if ("@wol".equals(spl[0])) {
			reply.add(new TextMessage("はい、睦月が用意するね！"));
			try{
				String url = URLEncoder.encode(spl[1], "UTF-8");
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
}
