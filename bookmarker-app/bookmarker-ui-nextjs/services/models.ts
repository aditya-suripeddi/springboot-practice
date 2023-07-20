
export interface BookmarksResponse {
    data: Bookmark[],
    totalElements: number,
    currentPage: number,
    totalPages: number,
    isFirst: boolean,
    isLast: boolean,
    hasNext: boolean,
    hasPrevious: boolean
}

export interface Bookmark {
    id: string,
    title: string,
    url: string,
    created_at: Date,
    updated_at: Date
}