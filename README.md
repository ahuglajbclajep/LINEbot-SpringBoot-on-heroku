# LINEbot-SpringBoot-on-heroku
heroku上で動くLINEのbotアカウントの中身

## Description
[これ](https://github.com/ahuglajbclajep/LINEbot-on-heroku) の
[Spring-Boot](https://projects.spring.io/spring-boot/) &
[LINE Messaging API](https://github.com/line/line-bot-sdk-java) &
[Gradle](https://gradle.org/) 版

## Install
### Create bot account
[ここ](https://business.line.me/ja/services/bot)で作る  
細かな手順は割愛、一番面倒なとこ

### Deploy to Heroku
下のボタンを押し、[ここ](https://github.com/line/line-bot-sdk-java/blob/master/sample-spring-boot-echo/README.md)を参考に設定  
**App Name は必ず設定すること**

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/ahuglajbclajep/LINEbot-SpringBoot-on-heroku)

## Examples Of Command
### @qr [string]
```
@qr https://github.com/ahuglajbclajep/LINEbot-SpringBoot-on-heroku
```
QRコードを生成する  
他のコマンドで作ったURLを共有するのにも使える

### @wol [question]
```
@wol graph Mickey Mouse curve
```
[WolframAlpha](http://www.wolframalpha.com)で検索する  
計算したり天気聞いたり

### @time [timeZoneID]
```
@time Europe/Paris
```
指定したタイムゾーンの現在時刻を教えてくれる  
引数がないか無効な場合[タイムゾーンの一覧](https://gist.githubusercontent.com/ahuglajbclajep/12899902e4e5331a84fa4fb19796d9fc/raw/20497ec3067437354cffc8a2e8c70710a7c1a5e9/ID.txt)を返す  
~~`@wol time paris`~~

その他画像やスタンプにも反応します

## Future Releases
* 発言をランダムにする
* 利用可能なコマンドの一覧を返す `@help` コマンドの実装

## Contribution
1. Fork it  
2. Create your feature branch  
3. Commit your changes  
4. Push to the branch  
5. Create new Pull Request

## Licence
[Apache-2.0](LICENSE)
