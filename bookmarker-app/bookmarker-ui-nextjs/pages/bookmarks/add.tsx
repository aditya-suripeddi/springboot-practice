import {NextPage} from "next";
import React, {useState} from "react";
import {saveBookmark} from "@/services/api";

const AddBookmark: NextPage = () => {

    const [title, setTitle] = useState("")
    const [url, setUrl] = useState("")
    const [message, setMessage] = useState<string|null>(null)

    const handleSubmit = async (e: React.SyntheticEvent)=> {
        e.preventDefault()
        // title can be empty, but url must not be empty
        if(!url){
            // for a more fancy implementation of alert
            // https://getbootstrap.com/docs/5.3/components/alerts/
            alert("Please enter the URL")
            return
        }
        const payload = {
            title,
            url
        }

        // only success case is covered,
        // for error / failure case we can
        // have a promise-then-error clause and then
        // specify if bookmark is succesfully created or not

        const response = await saveBookmark(payload) // assuming success
        console.log("-----------------")
        console.log("SaveBookmark Response:", response);

        // only when response is success we reset title and url to empty
        // and show the success message
        setTitle("")
        setUrl("")
        setMessage("Bookmark Saved Successfully")
    }

    return (
        <div>
            <div className="card">
                <div className="card=header text-center">
                    <h2>Create New Bookmark</h2>
                </div>
                <div className="card-body">
                    <div className="card-text">
                        {/*if message has value then show the div with message: */}
                        {message && <div className="alert alert-primary" role="alert">{message}</div>}
                        <form onSubmit={e => handleSubmit(e)}>
                            <div className="mb-3">
                                <label htmlFor="url" className="form-lable">URL</label>
                                <input type="text" className="form-control" id="url"
                                       value={url} onChange={e => setUrl(e.target.value)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="title" className="form-lable">Title</label>
                                <input type="text" className="form-control" id="title"
                                       value={title} onChange={e => setTitle(e.target.value)}/>
                            </div>
                            <button type="submit" className="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddBookmark