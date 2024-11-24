import {defineStore} from 'pinia'
import { reactive } from 'vue'

export const useNavStore = defineStore("nav", () => {
    const state = reactive({
        showRegisterDialog: false,
        showLoginDialog: false,
    })

    function changeRegisterDialogStatus() {
        state.showRegisterDialog = !state.showRegisterDialog
    }

    function changeLoginDialogStatus() {
        state.showLoginDialog = !state.showLoginDialog
    }
    return {state, changeLoginDialogStatus, changeRegisterDialogStatus}
})