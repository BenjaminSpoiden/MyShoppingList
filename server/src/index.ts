import express from "express"
import bodyParser from "body-parser"
import usersRoutes from "./routes/users"

const PORT = 4000

const main = async() => {
    const app = express()


    app.use(bodyParser.json())

    app.use("/users", usersRoutes)

    app.get("/", (req, res) => {
        
        res.send("Hello server")
    })

    app.listen(PORT, () => {
        console.log(`server ready at http://localhost:${PORT}`)
    })
}

main().catch(e => console.log("error: ", e))

