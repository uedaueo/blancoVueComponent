# blancoValueObject 上田版

このプロジェクトはblancoValueObjectの上田版です。
本家との相違は概ね以下の通りです。

* annotation 対応
* 明示的 import 対応
  * これは主に annotation のために用意しました。

## maven 対応について

### jar ファイルの作成

```
mvn package
```

### deploy

maven リポジトリは github 上のpublicリポジトリに作成される前提としてます。
~/.m2/settings.xml に以下のように記述することで、deploy可能となります。（useridとaccess_keyは実在のものをご使用下さい）

```~/.m2/settings.xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>userid</username>
      <password>access_key</password>
    </server>
  </servers>
</settings>
```

