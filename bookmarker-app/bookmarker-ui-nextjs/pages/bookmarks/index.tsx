import {GetServerSideProps, NextPage} from "next";
import React from "react";
import {BookmarksResponse} from "@/services/models";
import {fetchBookmarks} from "@/services/api";
import Bookmarks from "@/components/Bookmarks";
import SearchForm from "@/components/SearchForm";

// https://www.digitalocean.com/community/tutorials/react-typescript-with-react#functional-components

interface HomeProps {
    bookmarks:BookmarksResponse
    query?: string
}

// The Home Page
const Home: NextPage<HomeProps> = (props) => {
    return (
        <div>
            <SearchForm />
            <Bookmarks bookmarks={props.bookmarks} query={props.query}/>
        </div>
    )
}

// https://nextjs.org/docs/pages/building-your-application/data-fetching/get-server-side-props
export const getServerSideProps:GetServerSideProps = async(context) => {

    const {page = 1, query = ""} = context.query
    const bookmarks = await fetchBookmarks(parseInt(String(page)), String(query))

    return {
        props : {
            bookmarks,
            query
        } //will be passed to page component as props
    }
}
export default Home;