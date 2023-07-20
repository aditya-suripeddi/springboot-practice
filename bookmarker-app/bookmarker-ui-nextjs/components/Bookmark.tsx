import React from 'react'
import {Bookmark} from "@/services/models";
import Link from "next/link";

interface BookmarkProps {
    bookmark: Bookmark
}

const Bookmark:React.FC<BookmarkProps> = ({bookmark}) => {
    return (
        <div>
            <div className="alert alert-primary" role="alert">
                <h5>
                    <Link href={bookmark.url} className={"text-decoration-none"}>{bookmark.title}</Link>
                </h5>
            </div>
        </div>
    )
}

export default Bookmark;
