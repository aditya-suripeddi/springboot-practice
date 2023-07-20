import React from "react";
import NavBar from "@/components/NavBar";
import '@/styles/globals.css'
import type { AppProps } from 'next/app'


// import in _app.tsx for global use (across the application)
import "bootstrap/dist/css/bootstrap.min.css"


// if there is a common layout for your application
// you can factor it out and put it here in one place
//
// in the code below <NavBar/> is factored out and made common over all components
// of the Single Page Application
export default function App({ Component, pageProps }: AppProps) {
    return (
        <>
            <NavBar/>
            <main>
                <Component {...pageProps} />
            </main>
        </>
    )
}
