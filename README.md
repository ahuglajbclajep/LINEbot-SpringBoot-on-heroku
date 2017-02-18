# LINEbot-SpringBoot-on-heroku
heroku上で動くLINEのbotアカウントの中身

## Description
[これ](https://github.com/ahuglajbclajep/LINEbot-on-heroku)の
[Spring-Boot](https://projects.spring.io/spring-boot/)&
[LINE Messaging API](https://github.com/line/line-bot-sdk-java)&
[Gradle](https://gradle.org/)版

## Install
### Create bot account
[ここ](https://business.line.me/ja/services/bot)で作る   
細かな手順は割愛、一番面倒なとこ

### Deploy to Heroku
下のボタンを押し、[ここ](https://github.com/line/line-bot-sdk-java/blob/master/sample-spring-boot-echo/README.md)を参考に設定する

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/ahuglajbclajep/LINEbot-SpringBoot-on-heroku)

## Examples Of Command
順次追加予定

### @wol [question]
```
@wol graph Mickey Mouse curve
```  
[WolframAlpha](http://www.wolframalpha.com)で検索する  
計算したり天気聞いたり

その他画像やスタンプにも反応します

## Future Releases
* QRコードを生成する`@qr`コマンドの実装
* 指定したタイムゾーンの現在時刻を返す`@time`コマンドの実装
* 発言をランダムにする

## Contribution
1. Fork it  
2. Create your feature branch  
3. Commit your changes  
4. Push to the branch  
5. Create new Pull Request

## Licence
[Apache-2.0](LICENSE)