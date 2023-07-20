import React from "react";
import {BookmarksResponse} from "@/services/models";
import Link from "next/link";

interface PaginationProps {
    bookmarks: BookmarksResponse
    query?: string
}

const Pagination: React.FC<PaginationProps> = ({bookmarks, query}) => {

    const path = "/bookmarks"
    const queryParams =  ( query === undefined || query === "" ) ? {} : {query: query}

    const firstPage  = {pathname: path, query : { page: 1, ...queryParams} }
    const previousPage = {pathname:path, query: {page: bookmarks.currentPage - 1, ...queryParams}}
    const nextPage = {pathname:path, query: {page: bookmarks.currentPage + 1, ...queryParams}}
    const lastPage = {pathname:path, query: {page: bookmarks.totalPages, ...queryParams}}

  /*
    console.log("---------------------------------------");
    console.log(`nextPage:${JSON.stringify(nextPage)}`);
    console.log("---------------------------------------");
  */

    return (
        <div>
            <nav aria-label="Page navigation">
                <ul className="pagination justify-content-center">
                    <li className={"page-item " + (bookmarks.hasPrevious ? "" : "disabled")}>
                        <Link href={firstPage} className="page-link">First Page</Link>
                    </li>
                    <li className={"page-item " + (bookmarks.hasPrevious ? "" : "disabled")}>
                        <Link href={previousPage} className="page-link">Previous</Link>
                    </li>
                    <li className={"page-item " + (bookmarks.hasNext ? "" : "disabled")}>
                        <Link href={nextPage} className="page-link">Next</Link>
                    </li>
                    <li className={"page-item " + (bookmarks.hasNext ? "" : "disabled")}>
                        <Link href={lastPage} className="page-link">Last Page</Link>
                    </li>
                </ul>
            </nav>
        </div>
    )
}

export default Pagination;