●ストーリー
1. 予約可能なwyseの一覧を取得
2. wyseを予約する
3. wyseを返却する
4. 返却通知を行う

●機能一覧(機能視点)
1-1.予約可能なwyseの一覧を返却；実装済み
1-２.予約状況を返却する；未実装
2-1.即予約可能なwyseを貸出する；実装済み
2-2.予約オーダを積む；実装済み
3-1.返却処理を行う；実装済み
4-1.返却日ごとにメールにて通知を行う；未実装


●タスク一覧
・operationテーブル用にシーケンスを管理する機能を追加する
・コントローラが特定の例外を返却した際の挙動を実装する（xx応答で返す）
・reestTemplateの作成(基盤)
・reestTemplateでメール送信の機能を実装

＝＝＝＝＝＝＝
●send grid
curl --request POST \
  --url https://api.sendgrid.com/v3/mail/send \
  --header "Authorization: Bearer $SENDGRID_API_KEY" \
  --header 'Content-Type: application/json' \
  --data '{"personalizations": [{"to": [{"email": "test@example.com"}]}],"from": {"email": "test@example.com"},"subject": "Sending with SendGrid is Fun","content": [{"type": "text/plain", "value": "and easy to do anywhere, even with cURL"}]}'
