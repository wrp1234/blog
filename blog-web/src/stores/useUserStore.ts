import { defineStore } from 'pinia'
import { reactive } from 'vue'
import type { UserInfo } from '../types'

export const useUserStore = defineStore("user", () => {
    const state = reactive<UserInfo>({
        id: -1,
        username: '',
        photoId: -1,
        token: ''
    })

    function update(value: UserInfo) {
        state.id = value.id
        state.username = value.username
        state.photoId = value.photoId
        state.token = value.token
    }
    return { state, update }
})