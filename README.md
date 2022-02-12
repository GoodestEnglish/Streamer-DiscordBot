# 觀眾場小幫手 - Discord機械人版本
> 注意: 本模組需要使用MongoDB儲存觀眾資料, 還有使用Streamer-Mod讓你能夠在遊戲中邀請觀眾加入你的派對
>> [MongoDB](https://www.mongodb.com/)   
>> [Streamer-Mod](https://github.com/RealGoodestEnglish/Streamer-Mod)  

## 如何使用
__請確保你有一定[JDA](https://github.com/DV8FromTheWorld/JDA)和Java的知識__
```
下載本源碼, 並編輯StreamerConfig.java檔案
	> GUILD_ID (你的Discord伺服器ID)
	> USER_OWNER_ID (你的Discord帳號ID)
	> ROLE_YT_MEMBER (YT會員身份組ID)
	> ROLE_NITRO_BOOSTER (YT會員身份組ID)
	> ONLY_FOR_YT_MEMBERS (伺服器加成者身份組ID)
	> DISCORD_BOT_TOKEN (機械人Token)
	> MONGO_HOST (MongoDB資料庫IP, 必需要跟Streamer-Mod模組設置的資料庫IP一模一樣)
編譯源碼, 並運行該機械人
```

## 指令一覽
|指令|用途| 
|----------|:-------------:|
|!queue <玩家名稱>|讓觀眾加入等候隊列|


## 作者的話
這個機械人應該不會繼續更新了, 如果想自己編寫特別功能進去這個機械人, 歡迎
