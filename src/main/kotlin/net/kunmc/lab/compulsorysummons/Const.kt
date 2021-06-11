package net.kunmc.lab.compulsorysummons

class Const {
    companion object {
        // バトルロイヤルモード
        const val COMMAND_BATTLE_ROYAL_MODE: String = "battleRoyalMode"
        // サバイバルモード
        const val COMMAND_SURVIVAL_MODE: String = "survivalMode"
        // ストップ
        const val COMMAND_STOP: String = "stop"

        // 効果付与
        const val COMMAND_GIVE: String = "give"
        // 効果剥奪
        const val COMMAND_REMOVE: String = "remove"
        // TP人数設定
        const val COMMAND_SET_TP: String = "setTP"

        // 設定値確認
        const val COMMAND_SETTINGS: String = "settings"
        // 効果が付与されているプレイヤー確認
        const val COMMAND_ENABLE_PLAYER: String = "enablePlayer"
        // TPされる人数の確認
        const val COMMAND_SHOW_TP: String = "showTP"
        // コンフィグのリロード
        const val COMMAND_RELOAD: String = "reload"

    }
}