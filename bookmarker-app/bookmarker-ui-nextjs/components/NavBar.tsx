import {NextPage} from "next";
import Link from "next/link";


const NavBar: NextPage = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                <div className="container-fluid">
                    <Link href="/" className="navbar-brand">
                        Bookmark
                    </Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="col-lapse"
                            data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarCollapse">
                        <ul className="navbar-nav ms-auto">
                            <li className="nav-item">
                                <Link href="/bookmarks/add" className="nav-link">
                                    Add Bookmark
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    )
}

export default NavBar