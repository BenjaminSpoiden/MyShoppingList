import dotenv from "dotenv"
dotenv.config()
import "reflect-metadata";
import express from "express"
import usersRoutes from "./routes/users"
import AuthRoutes from "./routes/Auth"
import { createConnection } from "typeorm"
import cookieParser from "cookie-parser"
import { authentificateToken } from "./middleware/AuthMiddleware";

const PORT = process.env.PORT

const main = async() => {
    const app = express()
    await createConnection()

    app.use(express.json())
    app.use(cookieParser())

    app.use("/user", usersRoutes)
    app.use(AuthRoutes)
    app.get("/currentUser", authentificateToken, (req, res) => {
        //@ts-ignore
        res.send(req.user.user)
    })

    // app.get("*", currentUser)
    app.get("/", (_, res) => {
        res.send("Hello server")
    })

    app.get("/list", authentificateToken, (_, res) => {
        res.send("accessList")
    })

    app.listen(PORT, () => {
        console.log(`server ready at http://localhost:${PORT}`)
    })
}

main().catch(e => console.log("error: ", e))

