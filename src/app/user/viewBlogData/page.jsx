"use client";
import React, { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import styles from "../../ui/dashboard/viewBlogs/viewBlogs.module.css";

const BlogDetails = () => {
  const blogId = localStorage.getItem("blogId");
  const [blogData, setBlogData] = useState(null);
  const router = useRouter();

  console.log(blogId, "id");

  useEffect(() => {
    const fetchBlogData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8282/api/blogs/${blogId}`
        );
        const data = await response.json();
        setBlogData(data);
        console.log(data, "success");
      } catch (error) {
        console.error("Error fetching blog data:", error);
      }
    };

    fetchBlogData();
  }, [blogId]);

  if (!blogData) {
    return <div>Loading...</div>;
  }

  const handleDelete = async (blogId) => {
    try {
      const response = await fetch(
        `http://localhost:8282/api/blogs/delete/${blogId}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.ok) {
        // setUsers((prevBlogs) => prevBlogs.filter((blog) => blog.blogId !== blogId));
        router.push("/admin/dashboard/blogs");
      } else {
        console.error("Failed to delete blog:", response.statusText);
      }
    } catch (error) {
      console.error("Error deleting blog:", error);
    }
  };

  return (
    <div className={styles.mainPage}>
      {/* Left side - Static information */}
      <div className="col-3">
        <div className={styles.blogInfo}>
          <h3>
            Author : {blogData.userProfile.firstName}{" "}
            {blogData.userProfile.lastName}
          </h3>
          <br />
          <h4>Date: {blogData.date}</h4>
          <br />
          <h4>Category: {blogData.category.category}</h4>
          <br />
          <br />
          <button className={styles.buttonView} onClick={() => alert("View clicked")}>View</button>
          <button className={styles.buttonDelete} onClick={() => handleDelete(blogData.blogId)}>Delete</button>
        </div>
      </div>

      {/* Right side - Dynamic information */}
      <div className={styles.blogContent}>
        <h1>{blogData.title}</h1>
        <br />
        <br />
        <img
          src={blogData.imageUrl}
          alt="Blog Image"
          height={450}
          width={900}
        />
        <br />
        <br />
        <div className={styles.contents}>
        <p>{blogData.content}</p>
        </div>
      </div>
    </div>
  );
};

export default BlogDetails;
