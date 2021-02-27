import "reflect-metadata";
import express from "express"
import bodyParser from "body-parser"
import usersRoutes from "./routes/users"
import AuthRoutes from "./routes/Auth"
import { createConnection } from "typeorm"
import cookieParser from "cookie-parser"
import { currentUser } from "./middleware/AuthMiddleware";

const PORT = 4000

const main = async() => {
    const app = express()

    await createConnection()

    app.use(bodyParser.json())
    app.use(cookieParser())

    app.use("/users", usersRoutes)
    app.use(AuthRoutes)


    app.get("*", currentUser)
    app.get("/", (_, res) => {
        console.log("current User: ", res.locals.user)
        res.send("Hello server")
    })

    app.listen(PORT, () => {
        console.log(`server ready at http://localhost:${PORT}`)
    })
}

main().catch(e => console.log("error: ", e))

