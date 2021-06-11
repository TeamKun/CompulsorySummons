# CompulsorySummonsPlugin
ブロックを壊すとn人プレイヤーを強制召喚するプラグイン

## 動作環境
- Minecraft 1.16.5
- PaperMC 1.16.5

## コマンド一覧
- compulsorySummons
    - battleRoyalMode  
      バトルロイヤルモードを開始する
    - survivalMode  
      サバイバルモードを開始する
    - stop  
      現在実行しているモードを終了させる
    - give
        - <プレイヤー名>  
          強制召喚効果を付与する  
          (サバイバルモード中のみ実行可能)
    - remove
        - <プレイヤー名>  
          強制召喚効果を剥奪する  
          (サバイバルモード中のみ実行可能)
    - setTP
        - <召喚人数>  
          強制召喚する人数を設定する
    - settings
        - enablePlayer  
          効果を付与しているプレイヤーを表示
        - showTP  
          召喚人数を表示
        - reload  
          config.ymlを再読み込みする