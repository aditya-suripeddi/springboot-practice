import axios, {AxiosResponse} from "axios"
import {BookmarksResponse} from "@/services/models";

// https://nextjs.org/docs/app/api-reference/next-config-js/runtime-configuration
import getConfig from 'next/config'
const { serverRuntimeConfig, publicRuntimeConfig } = getConfig()
const getApiUrl =  () => {
     return serverRuntimeConfig.API_BASE_URL || publicRuntimeConfig.API_BASE_URL
}

export const fetchBookmarks = async (page:number, query:string): Promise<BookmarksResponse> => {

    console.log("serverRuntimeConfig:", serverRuntimeConfig)
    console.log("publicRuntimeConfig:", publicRuntimeConfig)
    //console.log("swcMinify(module.exports.swcMinify):", module.exports.swcMinify)
    console.log("swcMinify(getConfig().swcMinify):", getConfig().swcMinify)

    let url = `${getApiUrl()}/api/bookmarks?page=${page}`
    if( query ){
      url += `&query=${query}`
    }
    const res = await axios.get<BookmarksResponse>(url)
    return res.data
}


// instead of declaring an AddBookmarkRequest interface with title and url attributes
// we create an adhoc type {title:string, url:string} as show below:
export const saveBookmark = async (bookmark:{title:string, url:string}) => {

    console.log("serverRuntimeConfig:", serverRuntimeConfig)
    console.log("publicRuntimeConfig:", publicRuntimeConfig)
    //console.log("swcMinify(module.exports.swcMinify):", module.exports.swcMinify)
    console.log("swcMinify(getConfig().swcMinify):", getConfig().swcMinify)


    const res = await axios.post(`${getApiUrl()}/api/bookmarks`, bookmark)
    return res.data
}