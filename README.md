# 目次
### 📖処理概要
* メソッドを実行する
* 実行されたメソッドの結果をレスポンスに書き込む
### 💻INPUT&OUTPUT
* INPUT
  * 実行するメソッド（及びその引数）
  * 書き込むレスポンス
* OUTPUT
```
{
 "data":<メソッド実行結果>,
 "message":<実行結果のメッセージ（成功　OR　失敗）>
}
```
### ❌制約条件
* Javaのバージョン
  * 17
* 書込み対象レスポンス
  * HttpServletResponse
  * (and more...)
### 🎁公開方法について（Github Packages）
* 手順
  * 公開手順.mdを参照
