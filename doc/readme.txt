blancoValueObject は「バリューオブジェクト定義書」というExcel様式を記入するだけで 簡単に バリューオブジェクトのJavaクラスが作成できるようにするためのツールです。
 1.ごく普通の バリューオブジェクトのためのソースコードを生成します。
 2.生成後のソースコードは それ単独で動作することができます。
   余計な *.jarファイルなどの実行時ライブラリを一切必要としません。
 3.導入すると、ドキュメントとソースコードが必ず一致するという効果があります。

[開発者]
 1.伊賀敏樹 (Tosiki Iga / いがぴょん): 開発および維持メンテ担当
 2.山本耕司 (ymoto) : 仕様決定およびリリース判定担当

[ライセンス]
 1.blancoValueObject は ライセンス として GNU Lesser General Public License を採用しています。

[依存するライブラリ]
blancoValueObjectは下記のライブラリを利用しています。
※各オープンソース・プロダクトの提供者に感謝します。
 1.JExcelApi - Java Excel API - A Java API to read, write and modify Excel spreadsheets
     http://jexcelapi.sourceforge.net/
     http://sourceforge.net/projects/jexcelapi/
     http://www.andykhan.com/jexcelapi/ 
   概要: JavaからExcelブック形式を読み書きするためのライブラリです。
   ライセンス: GNU Lesser General Public License
 2.blancoCg
   概要: ソースコード生成ライブラリ
   ライセンス: GNU Lesser General Public License
 3.その他の blanco Framework
   概要: このプロダクトは それ自身が blanco Frameworkにより自動生成されています。
         このプロダクトは 実行時に blanco Framework各種プロダクトに依存して動作します。
   ライセンス: GNU Lesser General Public License
