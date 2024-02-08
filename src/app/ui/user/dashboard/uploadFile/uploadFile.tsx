'use client'
import React from 'react'
import { useState } from 'react'
import styles from './uploadFile.module.css'

const UploadFile = () => {
   const [file, setFile] = useState<File>()

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    if (!file) return

    try {
      const data = new FormData()
      data.set('file', file)

      const res = await fetch('/api/upload', {
        method: 'POST',
        body: data
      })
     
      if (!res.ok) throw new Error(await res.text())
    } catch (e: any) {
      
      console.error(e)
    }
  }

  return (
    <form onSubmit={onSubmit}>
      <input className={styles.chooseFile}
        type="file"
        name="file"
        onChange={(e) => setFile(e.target.files?.[0])}
          />
        <br></br>
      <input className={styles.upload} type="submit" value="Upload" />
    </form>
  )
}

export default UploadFile
