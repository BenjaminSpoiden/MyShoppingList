import { Request, Response, NextFunction } from "express";
import jwt from "jsonwebtoken"
import { User } from "../entity/User";


export const requireAuth = (req: Request, res: Response, next: NextFunction) => {

    const token = req.cookies.jwt_cookie

    if(!token) res.status(400).send("User not authentificated")

    jwt.verify(token, "secret cookie placeholder", (err: any, decodedToken: any) => {
        if(err) {
            console.log(err.message)
            res.status(400).send("Something went wrong.")
        }else {
            console.log("decodedToken", decodedToken)
            next()
        }
    })  
}

export const currentUser = (req: Request, res: Response, next: NextFunction) => {

    const token = req.cookies.jwt_cookie

    if(!token) {
        res.locals.user = null
        next()
    }

    jwt.verify(token, "secret cookie placeholder", async (err: any, decodedToken: any) => {
        if(err) {
            res.locals.user = null
            next()
        } 
        else {
            const user = await User.findOne({uuid: decodedToken.id})
            res.locals.user = user
            next()
        }
    })
}