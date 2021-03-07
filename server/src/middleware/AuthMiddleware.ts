import { Request, Response, NextFunction } from "express";
import jwt from "jsonwebtoken"


export const authentificateToken = (req: Request, res: Response, next: NextFunction) => {
    const authHeader = req.headers['authorization']
    const token = authHeader && authHeader.split(' ')[1]

    if(!token) res.sendStatus(401)
    //@ts-ignore
    jwt.verify(token, process.env.ACCESS_TOKEN_SECRET, (err, user) => {
        if(err) res.sendStatus(403)
        //@ts-ignore
        req.user = user
        next()
    })

}
