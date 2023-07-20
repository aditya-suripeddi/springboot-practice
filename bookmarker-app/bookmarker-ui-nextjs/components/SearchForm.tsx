import React, { useState } from "react" // react hooks
import { useRouter } from "next/router"

const SearchForm: React.FC = () => {

    // handle programmatic page navigation
    const router = useRouter()

    // react useState hook, initial state is empty string
    const [query, setQuery] = useState("");

    // form submission handler
    // we are concerned with event type, so we use React.SyntenticEvent
    const handleSearch = async (e: React.SyntheticEvent) => {
        e.preventDefault();
        if( query === "" ) {
            // redirect to bookmarks index/listing page when user does not enter any search term
            await router.push("/bookmarks")
            return;
        }
        await router.push(`/bookmarks?page=1&query=${query}`)
    };


    return (
        <div className={"pb-3"}>
            {/* form: HTTP-GET method, call handleSearch() on submit */}
            <form method="get"  onSubmit={handleSearch} >
                <div className="row g-3 align-items-center">
                    <div className="col">
                        {/*update the query variable value onChange event of input*/}
                        <input className="form-control"
                               type="search" name="query"
                               value={query}
                               onChange={(e) => setQuery(e.target.value)} />
                    </div>
                    <div className="col-auto">
                        <button type="submit" className="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default SearchForm