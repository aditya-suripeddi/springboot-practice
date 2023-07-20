import React from "react"
import exp from "constants";
import {BookmarksResponse} from "@/services/models";
import Bookmark from "@/components/Bookmark";
import Pagination from "@/components/Pagination";

interface BookmarksProps {
    bookmarks: BookmarksResponse
    query?:string
}

const Bookmarks: React.FC<BookmarksProps> = ({bookmarks,query}) => {
    return (
        <div>
            <Pagination bookmarks={bookmarks} query={query}/>
            {bookmarks.data.map(bookmark => <Bookmark key={bookmark.id} bookmark={bookmark} />)}
        </div>
    )
}

export default Bookmarks;