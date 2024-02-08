// "use client";
// import { useRouter } from "next/navigation";
// import React, { useState } from "react";
// import styles from "../../../../ui/dashboard/blogs/addBlogs/addBlogs.module.css";
// import { userId } from "../../../../constants/constant";

// const AddBlogForm = () => {
//   const router = useRouter();
//   const id = userId;
//   const baseUrl = 'http://localhost:8282/api'

//   const [blog, setBlog] = useState({
//     title: "",
//     category: "",
//     content: "",
//     imageUrl: "",
//     userProfile: {
//       userId: id,
//     },
//   });

//   const [fileUpload, setFileUpload] = useState({
//     file: null,
//   });

//   const [isFileUploading, setIsFileUploading] = useState(false);
//   const [submitButton, setSubmitButton] = useState(false);

//   const handleChange = (e) => {
//     const { name, value, files } = e.target;

//     if (name === "file") {
//       setFileUpload(() => ({
//         [name]: files[0],
//       }));
//     } else {
//       setBlog((prevBlog) => ({
//         ...prevBlog,
//         [name]: value,
//       }));
//     }
//   };

//   // const handleFileUpload = async () => {
//   //   setIsFileUploading(true);

//   //   const formData = new FormData();
//   //   formData.append("file", fileUpload.file);

//   //   try {
//   //     const response = await fetch("http://localhost:8282/api/blogFiles/upload", {
//   //       method: "POST",
//   //       body: formData,
//   //     });

//   //     if (response.ok) {
//   //       const data = await response.json();
//   //       console.log("File uploaded successfully:", data);

//   //       setBlog((prevBlog) => ({
//   //         ...prevBlog,
//   //         blogFiles: data,
//   //       }));

//   //       return true;
//   //     } else {
//   //       console.error("Failed to upload file");
//   //       return false;
//   //     }
//   //   } catch (error) {
//   //     console.error("Error uploading file:", error);
//   //     return false;
//   //   } finally {
//   //     setIsFileUploading(false);
//   //   }
//   // };

//   const handleFileUpload = async () => {
//     setIsFileUploading(true);
  
//     // Fetch the user's available size
//     try {
      
//       const userResponse = await fetch(`${baseUrl}/user/${id}`);
//       const userData = await userResponse.json();
  
//       const userAvailableSize = userData.sizeAvailable;
  
//       // Check if the file size is within the available size limit
//       if (fileUpload.file.size <= userAvailableSize) {
//         const formData = new FormData();
//         formData.append("file", fileUpload.file);
  
//         try {
//           const response = await fetch(`${baseUrl}/blogFiles/upload`, {
//             method: "POST",
//             body: formData,
//           });
  
//           if (response.ok) {
//             const data = await response.json();
//             console.log("File uploaded successfully:", data);
  
//             setBlog((prevBlog) => ({
//               ...prevBlog,
//               blogFiles: data,
//             }));
  
//             return true;
//           } else {
//             console.error("Failed to upload file");
//             return false;
//           }
//         } catch (error) {
//           console.error("Error uploading file:", error);
//           return false;
//         }
//       } else {
//         console.error("File size exceeds available size limit");
//         return false;
//       }
//     } catch (error) {
//       console.error("Error fetching user data:", error);
//       return false;
//     } finally {
//       setIsFileUploading(false);
//     };
//   };
  

//   const handleSendBlogData = async () => {
//     try {
//       const response = await fetch(`${baseUrl}/blogs/add`, {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(blog),
//       });

//       if (response.ok) {
//         console.log("Blog submitted successfully");
//         router.push("/admin/dashboard/blogs");
//       } else {
//         console.error("Failed to submit blog");
//       }
//     } catch (error) {
//       console.error("Error submitting blog:", error);
//     }
//   };

//   const handleUploadAndSubmit = async () => {
//     const fileUploadSuccess = await handleFileUpload();

//     if (fileUploadSuccess) {
//       setSubmitButton(true);
//     }
//   };

//   return (
//     <div className={styles.mainPage}>
//       <div className={styles.AddBlogForm}>
//         <div className={styles.formContent}>
//           <form>
//           <label className={styles.label} htmlFor="blogTitle">
//               Title:
//             </label>
//             <input
//               className={styles.input}
//               type="text"
//               id="blogTitle"
//               name="title"
//               value={blog.title}
//               onChange={handleChange}
//               required
//             />
//             <br />
//             <br />

//             <label className={styles.label} htmlFor="category">
//               Category:
//             </label>
//             <input
//               className={styles.input}
//               type="text"
//               id="category"
//               name="category"
//               value={blog.category}
//               onChange={handleChange}
//               required
//             />
//             <br />
//             <br />

//             <label className={styles.label} htmlFor="blogContent">
//               Content:
//             </label>
//             <textarea
//               className={styles.input}
//               id="blogContent"
//               name="content"
//               value={blog.content}
//               onChange={handleChange}
//               required
//             />
//             <br />
//             <br />

//             <label className={styles.label} htmlFor="blogTags">ImageUrl:</label>
//             <input className={styles.input}
//               type="text"
//               id="blogTags"
//               name="imageUrl"
//               value={blog.imageUrl}
//               onChange={handleChange}
//               required
//             />
//             <br />
//             <br />
//             <label className={styles.label} htmlFor="file">Choose a file:</label>
//             <input
//               type="file"
//               id="file"
//               name="file"
//               onChange={handleChange}
//             />
//             <button
//               className={styles.button}
//               type="button"
//               onClick={handleUploadAndSubmit}
//               disabled={isFileUploading || submitButton}
//             >
//               {isFileUploading ? "Uploading..." : "Upload File"}
//             </button>
//             <br />
//             <br />

//             <button
//               className={styles.button}
//               type="submit"
//               onClick={handleSendBlogData}
//               disabled={!submitButton}
//             >
//               Submit Data
//             </button>
//           </form>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default AddBlogForm;




"use client";
import { useRouter } from "next/navigation";
import React, { useState, useEffect } from "react";
import styles from "../../../../ui/dashboard/blogs/addBlogs/addBlogs.module.css";
import { userId } from "../../../../constants/constant";

const AddBlogForm = () => {
  const router = useRouter();
  const id = userId;
  const baseUrl = 'http://localhost:8282/api'

  const [blog, setBlog] = useState({
    title: "",
    content: "",
    imageUrl: "",
    category: {
      categoryId: '',
    },
    userProfile: {
      userId: id,
    },
  });

  const [fileUpload, setFileUpload] = useState({
    file: null,
  });

  const [isFileUploading, setIsFileUploading] = useState(false);
  const [submitButton, setSubmitButton] = useState(false);
  const [categories, setCategories] = useState([])

  const handleChange = (e) => {
    const { name, value, files } = e.target;

    if (name === "file") {
      setFileUpload(() => ({
        file: files[0],
      }));
    } else if (name === "categoryId") {
      setBlog((prevBlog) => ({
        ...prevBlog,
        category: {
          categoryId: value,
        },
      }));
    } else {
      setBlog((prevBlog) => ({
        ...prevBlog,
        [name]: value,
      }));
    }
  };

  const getCategories = async () => {
    try {
      const response = await fetch(`${baseUrl}/category/getCategories`);
      if (response.ok) {
        const data = await response.json();
        setCategories(data);
      } else {
        console.error('Failed to fetch categories. Server returned:', response.status, response.statusText);
      }
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };

  const handleFileUpload = async () => {
    setIsFileUploading(true);
  
    // Fetch the user's available size
    try {
      
      const userResponse = await fetch(`${baseUrl}/user/${id}`);
      const userData = await userResponse.json();
  
      const userAvailableSize = userData.sizeAvailable;
  
      // Check if the file size is within the available size limit
      if (fileUpload.file.size <= userAvailableSize) {
        const formData = new FormData();
        formData.append("file", fileUpload.file);
  
        try {
          const response = await fetch(`${baseUrl}/blogFiles/upload`, {
            method: "POST",
            body: formData,
          });
  
          if (response.ok) {
            const data = await response.json();
            console.log("File uploaded successfully:", data);
  
            setBlog((prevBlog) => ({
              ...prevBlog,
              blogFiles: data,
            }));
  
            return true;
          } else {
            console.error("Failed to upload file");
            return false;
          }
        } catch (error) {
          console.error("Error uploading file:", error);
          return false;
        }
      } else {
        console.error("File size exceeds available size limit");
        return false;
      }
    } catch (error) {
      console.error("Error fetching user data:", error);
      return false;
    } finally {
      setIsFileUploading(false);
    };
  };

  const handleSendBlogData = async () => {
    try {
      const response = await fetch(`${baseUrl}/blogs/add`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(blog),
      });

      if (response.ok) {
        console.log("Blog submitted successfully");
        router.push("/admin/dashboard/myBlogs");
      } else {
        console.error("Failed to submit blog");
      }
    } catch (error) {
      console.error("Error submitting blog:", error);
    }
  };

  const handleUploadAndSubmit = async () => {
    const fileUploadSuccess = await handleFileUpload();

    if (fileUploadSuccess) {
      setSubmitButton(true);
    }
  };

  useEffect(() => {
    getCategories();
  }, []);

  return (
    <div className={styles.mainPage}>
      <div className={styles.AddBlogForm}>
        <div className={styles.formContent}>
          <form>
            <label className={styles.label} htmlFor="blogTitle">
              Title:
            </label>
            <input
              className={styles.input}
              type="text"
              id="blogTitle"
              name="title"
              value={blog.title}
              onChange={handleChange}
              required
            />
            <br />
            <br />

            <label className={styles.label} htmlFor="categoryId">
              Category:
            </label>
            <select
              className={styles.input}
              name="categoryId"
              value={blog.category.categoryId}
              onChange={handleChange}
              required
            >
              <option value=''>Select Category</option>
              {categories.map((category) => (
                <option key={category.categoryId} value={category.categoryId}>
                  {category.category}
                </option>
              ))}
            </select>
            <br />
            <br />

            <label className={styles.label} htmlFor="blogContent">
              Content:
            </label>
            <textarea
              className={styles.input}
              id="blogContent"
              name="content"
              value={blog.content}
              onChange={handleChange}
              required
            />
            <br />
            <br />

            <label className={styles.label} htmlFor="blogTags">ImageUrl:</label>
            <input className={styles.input}
              type="text"
              id="blogTags"
              name="imageUrl"
              value={blog.imageUrl}
              onChange={handleChange}
              required
            />
            <br />
            <br />

            <label className={styles.label} htmlFor="file">Choose a file:</label>
            <input
              type="file"
              id="file"
              name="file"
              onChange={handleChange}
            />
            <button
              className={styles.button}
              type="button"
              onClick={handleUploadAndSubmit}
              disabled={isFileUploading || submitButton}
            >
              {isFileUploading ? "Uploading..." : "Upload File"}
            </button>
            <br />
            <br />

            <button
              className={styles.button}
              type="submit"
              onClick={handleSendBlogData}
              disabled={!submitButton}
            >
              Submit Data
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddBlogForm;
